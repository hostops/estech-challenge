import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISystemDevice } from 'app/shared/model/system-device.model';
import { SystemDeviceService } from './system-device.service';
import { ISystemDeviceType } from 'app/shared/model/system-device-type.model';
import { SystemDeviceTypeService } from 'app/entities/system-device-type';
import { ISystemUnit } from 'app/shared/model/system-unit.model';
import { SystemUnitService } from 'app/entities/system-unit';

@Component({
    selector: 'jhi-system-device-update',
    templateUrl: './system-device-update.component.html'
})
export class SystemDeviceUpdateComponent implements OnInit {
    systemDevice: ISystemDevice;
    isSaving: boolean;

    systemdevicetypes: ISystemDeviceType[];

    systemdevices: ISystemDevice[];

    systemunits: ISystemUnit[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected systemDeviceService: SystemDeviceService,
        protected systemDeviceTypeService: SystemDeviceTypeService,
        protected systemUnitService: SystemUnitService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ systemDevice }) => {
            this.systemDevice = systemDevice;
        });
        this.systemDeviceTypeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISystemDeviceType[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISystemDeviceType[]>) => response.body)
            )
            .subscribe((res: ISystemDeviceType[]) => (this.systemdevicetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.systemDeviceService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISystemDevice[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISystemDevice[]>) => response.body)
            )
            .subscribe((res: ISystemDevice[]) => (this.systemdevices = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.systemUnitService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISystemUnit[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISystemUnit[]>) => response.body)
            )
            .subscribe((res: ISystemUnit[]) => (this.systemunits = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.systemDevice.id !== undefined) {
            this.subscribeToSaveResponse(this.systemDeviceService.update(this.systemDevice));
        } else {
            this.subscribeToSaveResponse(this.systemDeviceService.create(this.systemDevice));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemDevice>>) {
        result.subscribe((res: HttpResponse<ISystemDevice>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSystemDeviceTypeById(index: number, item: ISystemDeviceType) {
        return item.id;
    }

    trackSystemDeviceById(index: number, item: ISystemDevice) {
        return item.id;
    }

    trackSystemUnitById(index: number, item: ISystemUnit) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
