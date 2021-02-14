import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISystemUnit } from 'app/shared/model/system-unit.model';
import { SystemUnitService } from './system-unit.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-system-unit-update',
    templateUrl: './system-unit-update.component.html'
})
export class SystemUnitUpdateComponent implements OnInit {
    systemUnit: ISystemUnit;
    isSaving: boolean;

    users: IUser[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected systemUnitService: SystemUnitService,
        protected userService: UserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ systemUnit }) => {
            this.systemUnit = systemUnit;
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.systemUnit.id !== undefined) {
            this.subscribeToSaveResponse(this.systemUnitService.update(this.systemUnit));
        } else {
            this.subscribeToSaveResponse(this.systemUnitService.create(this.systemUnit));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemUnit>>) {
        result.subscribe((res: HttpResponse<ISystemUnit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
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
