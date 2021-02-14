import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemUnit } from 'app/shared/model/system-unit.model';

@Component({
    selector: 'jhi-system-unit-detail',
    templateUrl: './system-unit-detail.component.html'
})
export class SystemUnitDetailComponent implements OnInit {
    systemUnit: ISystemUnit;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemUnit }) => {
            this.systemUnit = systemUnit;
        });
    }

    previousState() {
        window.history.back();
    }
}
