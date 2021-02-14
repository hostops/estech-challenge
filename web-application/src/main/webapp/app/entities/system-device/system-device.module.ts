import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WpcemSharedModule } from 'app/shared';
import {
    SystemDeviceComponent,
    SystemDeviceDetailComponent,
    SystemDeviceUpdateComponent,
    SystemDeviceDeletePopupComponent,
    SystemDeviceDeleteDialogComponent,
    systemDeviceRoute,
    systemDevicePopupRoute
} from './';

const ENTITY_STATES = [...systemDeviceRoute, ...systemDevicePopupRoute];

@NgModule({
    imports: [WpcemSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SystemDeviceComponent,
        SystemDeviceDetailComponent,
        SystemDeviceUpdateComponent,
        SystemDeviceDeleteDialogComponent,
        SystemDeviceDeletePopupComponent
    ],
    entryComponents: [
        SystemDeviceComponent,
        SystemDeviceUpdateComponent,
        SystemDeviceDeleteDialogComponent,
        SystemDeviceDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WpcemSystemDeviceModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
