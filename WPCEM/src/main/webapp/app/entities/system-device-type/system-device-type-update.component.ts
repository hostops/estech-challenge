import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISystemDeviceType } from 'app/shared/model/system-device-type.model';
import { SystemDeviceTypeService } from './system-device-type.service';

@Component({
    selector: 'jhi-system-device-type-update',
    templateUrl: './system-device-type-update.component.html'
})
export class SystemDeviceTypeUpdateComponent implements OnInit {
    systemDeviceType: ISystemDeviceType;
    isSaving: boolean;

    constructor(protected systemDeviceTypeService: SystemDeviceTypeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ systemDeviceType }) => {
            this.systemDeviceType = systemDeviceType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.systemDeviceType.id !== undefined) {
            this.subscribeToSaveResponse(this.systemDeviceTypeService.update(this.systemDeviceType));
        } else {
            this.subscribeToSaveResponse(this.systemDeviceTypeService.create(this.systemDeviceType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemDeviceType>>) {
        result.subscribe((res: HttpResponse<ISystemDeviceType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
