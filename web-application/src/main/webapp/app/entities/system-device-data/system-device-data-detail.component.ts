import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemDeviceData } from 'app/shared/model/system-device-data.model';

@Component({
    selector: 'jhi-system-device-data-detail',
    templateUrl: './system-device-data-detail.component.html'
})
export class SystemDeviceDataDetailComponent implements OnInit {
    systemDeviceData: ISystemDeviceData;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemDeviceData }) => {
            this.systemDeviceData = systemDeviceData;
        });
    }

    previousState() {
        window.history.back();
    }
}
