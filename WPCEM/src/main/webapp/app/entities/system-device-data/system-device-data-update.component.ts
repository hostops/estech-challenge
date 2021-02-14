import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ISystemDeviceData } from 'app/shared/model/system-device-data.model';
import { SystemDeviceDataService } from './system-device-data.service';
import { ISystemDevice } from 'app/shared/model/system-device.model';
import { SystemDeviceService } from 'app/entities/system-device';

@Component({
    selector: 'jhi-system-device-data-update',
    templateUrl: './system-device-data-update.component.html'
})
export class SystemDeviceDataUpdateComponent implements OnInit {
    systemDeviceData: ISystemDeviceData;
    isSaving: boolean;

    systemdevices: ISystemDevice[];
    timestamp: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected systemDeviceDataService: SystemDeviceDataService,
        protected systemDeviceService: SystemDeviceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ systemDeviceData }) => {
            this.systemDeviceData = systemDeviceData;
            this.timestamp = this.systemDeviceData.timestamp != null ? this.systemDeviceData.timestamp.format(DATE_TIME_FORMAT) : null;
        });
        this.systemDeviceService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISystemDevice[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISystemDevice[]>) => response.body)
            )
            .subscribe((res: ISystemDevice[]) => (this.systemdevices = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.systemDeviceData.timestamp = this.timestamp != null ? moment(this.timestamp, DATE_TIME_FORMAT) : null;
        if (this.systemDeviceData.id !== undefined) {
            this.subscribeToSaveResponse(this.systemDeviceDataService.update(this.systemDeviceData));
        } else {
            this.subscribeToSaveResponse(this.systemDeviceDataService.create(this.systemDeviceData));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemDeviceData>>) {
        result.subscribe((res: HttpResponse<ISystemDeviceData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSystemDeviceById(index: number, item: ISystemDevice) {
        return item.id;
    }
}
