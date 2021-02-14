import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemDevice } from 'app/shared/model/system-device.model';

@Component({
    selector: 'jhi-system-device-detail',
    templateUrl: './system-device-detail.component.html'
})
export class SystemDeviceDetailComponent implements OnInit {
    systemDevice: ISystemDevice;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemDevice }) => {
            this.systemDevice = systemDevice;
            var points = this.systemDevice.data;

            setTimeout(function() {
                let canvas = document.getElementById('trend-graph');
                let ctx = canvas.getContext('2d');
                let width = canvas.width;
                let height = canvas.height;
                let name;
                let x = width - 10;

                points.forEach(function(element) {
                    var y = height - element.dataValue;
                    if (name == null) {
                        name = element.name;
                    }
                    if (name == element.name) {
                        ctx.fillRect(x, y, 1, 1);
                        console.log(element);
                        console.log(x);
                        console.log(y);

                        x -= 5;
                    }
                });
            }, 1000);
        });
    }

    previousState() {
        window.history.back();
    }
}
