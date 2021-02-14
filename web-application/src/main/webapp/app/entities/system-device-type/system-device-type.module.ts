import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WpcemSharedModule } from 'app/shared';
import {
    SystemDeviceTypeComponent,
    SystemDeviceTypeDetailComponent,
    SystemDeviceTypeUpdateComponent,
    SystemDeviceTypeDeletePopupComponent,
    SystemDeviceTypeDeleteDialogComponent,
    systemDeviceTypeRoute,
    systemDeviceTypePopupRoute
} from './';

const ENTITY_STATES = [...systemDeviceTypeRoute, ...systemDeviceTypePopupRoute];

@NgModule({
    imports: [WpcemSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SystemDeviceTypeComponent,
        SystemDeviceTypeDetailComponent,
        SystemDeviceTypeUpdateComponent,
        SystemDeviceTypeDeleteDialogComponent,
        SystemDeviceTypeDeletePopupComponent
    ],
    entryComponents: [
        SystemDeviceTypeComponent,
        SystemDeviceTypeUpdateComponent,
        SystemDeviceTypeDeleteDialogComponent,
        SystemDeviceTypeDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WpcemSystemDeviceTypeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
