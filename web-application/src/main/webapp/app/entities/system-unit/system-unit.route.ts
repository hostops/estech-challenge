import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SystemUnit } from 'app/shared/model/system-unit.model';
import { SystemUnitService } from './system-unit.service';
import { SystemUnitComponent } from './system-unit.component';
import { SystemUnitDetailComponent } from './system-unit-detail.component';
import { SystemUnitUpdateComponent } from './system-unit-update.component';
import { SystemUnitDeletePopupComponent } from './system-unit-delete-dialog.component';
import { ISystemUnit } from 'app/shared/model/system-unit.model';

@Injectable({ providedIn: 'root' })
export class SystemUnitResolve implements Resolve<ISystemUnit> {
    constructor(private service: SystemUnitService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISystemUnit> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SystemUnit>) => response.ok),
                map((systemUnit: HttpResponse<SystemUnit>) => systemUnit.body)
            );
        }
        return of(new SystemUnit());
    }
}

export const systemUnitRoute: Routes = [
    {
        path: '',
        component: SystemUnitComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wpcemApp.systemUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SystemUnitDetailComponent,
        resolve: {
            systemUnit: SystemUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SystemUnitUpdateComponent,
        resolve: {
            systemUnit: SystemUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SystemUnitUpdateComponent,
        resolve: {
            systemUnit: SystemUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const systemUnitPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SystemUnitDeletePopupComponent,
        resolve: {
            systemUnit: SystemUnitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemUnit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
