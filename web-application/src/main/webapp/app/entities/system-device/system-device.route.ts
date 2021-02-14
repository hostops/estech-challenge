import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SystemDevice } from 'app/shared/model/system-device.model';
import { SystemDeviceService } from './system-device.service';
import { SystemDeviceComponent } from './system-device.component';
import { SystemDeviceDetailComponent } from './system-device-detail.component';
import { SystemDeviceUpdateComponent } from './system-device-update.component';
import { SystemDeviceDeletePopupComponent } from './system-device-delete-dialog.component';
import { ISystemDevice } from 'app/shared/model/system-device.model';

@Injectable({ providedIn: 'root' })
export class SystemDeviceResolve implements Resolve<ISystemDevice> {
    constructor(private service: SystemDeviceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISystemDevice> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SystemDevice>) => response.ok),
                map((systemDevice: HttpResponse<SystemDevice>) => systemDevice.body)
            );
        }
        return of(new SystemDevice());
    }
}

export const systemDeviceRoute: Routes = [
    {
        path: '',
        component: SystemDeviceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wpcemApp.systemDevice.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SystemDeviceDetailComponent,
        resolve: {
            systemDevice: SystemDeviceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDevice.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SystemDeviceUpdateComponent,
        resolve: {
            systemDevice: SystemDeviceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDevice.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SystemDeviceUpdateComponent,
        resolve: {
            systemDevice: SystemDeviceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDevice.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const systemDevicePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SystemDeviceDeletePopupComponent,
        resolve: {
            systemDevice: SystemDeviceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDevice.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
