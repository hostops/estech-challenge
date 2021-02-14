import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SystemDeviceType } from 'app/shared/model/system-device-type.model';
import { SystemDeviceTypeService } from './system-device-type.service';
import { SystemDeviceTypeComponent } from './system-device-type.component';
import { SystemDeviceTypeDetailComponent } from './system-device-type-detail.component';
import { SystemDeviceTypeUpdateComponent } from './system-device-type-update.component';
import { SystemDeviceTypeDeletePopupComponent } from './system-device-type-delete-dialog.component';
import { ISystemDeviceType } from 'app/shared/model/system-device-type.model';

@Injectable({ providedIn: 'root' })
export class SystemDeviceTypeResolve implements Resolve<ISystemDeviceType> {
    constructor(private service: SystemDeviceTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISystemDeviceType> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SystemDeviceType>) => response.ok),
                map((systemDeviceType: HttpResponse<SystemDeviceType>) => systemDeviceType.body)
            );
        }
        return of(new SystemDeviceType());
    }
}

export const systemDeviceTypeRoute: Routes = [
    {
        path: '',
        component: SystemDeviceTypeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wpcemApp.systemDeviceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SystemDeviceTypeDetailComponent,
        resolve: {
            systemDeviceType: SystemDeviceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SystemDeviceTypeUpdateComponent,
        resolve: {
            systemDeviceType: SystemDeviceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SystemDeviceTypeUpdateComponent,
        resolve: {
            systemDeviceType: SystemDeviceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const systemDeviceTypePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SystemDeviceTypeDeletePopupComponent,
        resolve: {
            systemDeviceType: SystemDeviceTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
