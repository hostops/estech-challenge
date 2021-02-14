import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SystemDeviceData } from 'app/shared/model/system-device-data.model';
import { SystemDeviceDataService } from './system-device-data.service';
import { SystemDeviceDataComponent } from './system-device-data.component';
import { SystemDeviceDataDetailComponent } from './system-device-data-detail.component';
import { SystemDeviceDataUpdateComponent } from './system-device-data-update.component';
import { SystemDeviceDataDeletePopupComponent } from './system-device-data-delete-dialog.component';
import { ISystemDeviceData } from 'app/shared/model/system-device-data.model';

@Injectable({ providedIn: 'root' })
export class SystemDeviceDataResolve implements Resolve<ISystemDeviceData> {
    constructor(private service: SystemDeviceDataService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISystemDeviceData> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SystemDeviceData>) => response.ok),
                map((systemDeviceData: HttpResponse<SystemDeviceData>) => systemDeviceData.body)
            );
        }
        return of(new SystemDeviceData());
    }
}

export const systemDeviceDataRoute: Routes = [
    {
        path: '',
        component: SystemDeviceDataComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'wpcemApp.systemDeviceData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SystemDeviceDataDetailComponent,
        resolve: {
            systemDeviceData: SystemDeviceDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SystemDeviceDataUpdateComponent,
        resolve: {
            systemDeviceData: SystemDeviceDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SystemDeviceDataUpdateComponent,
        resolve: {
            systemDeviceData: SystemDeviceDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const systemDeviceDataPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SystemDeviceDataDeletePopupComponent,
        resolve: {
            systemDeviceData: SystemDeviceDataResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wpcemApp.systemDeviceData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
