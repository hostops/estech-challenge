import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemDeviceType } from 'app/shared/model/system-device-type.model';

@Component({
    selector: 'jhi-system-device-type-detail',
    templateUrl: './system-device-type-detail.component.html'
})
export class SystemDeviceTypeDetailComponent implements OnInit {
    systemDeviceType: ISystemDeviceType;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemDeviceType }) => {
            this.systemDeviceType = systemDeviceType;
        });
    }

    previousState() {
        window.history.back();
    }
}
