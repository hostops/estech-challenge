import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WpcemSharedModule } from 'app/shared';
import {
    SystemDeviceDataComponent,
    SystemDeviceDataDetailComponent,
    SystemDeviceDataUpdateComponent,
    SystemDeviceDataDeletePopupComponent,
    SystemDeviceDataDeleteDialogComponent,
    systemDeviceDataRoute,
    systemDeviceDataPopupRoute
} from './';

const ENTITY_STATES = [...systemDeviceDataRoute, ...systemDeviceDataPopupRoute];

@NgModule({
    imports: [WpcemSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SystemDeviceDataComponent,
        SystemDeviceDataDetailComponent,
        SystemDeviceDataUpdateComponent,
        SystemDeviceDataDeleteDialogComponent,
        SystemDeviceDataDeletePopupComponent
    ],
    entryComponents: [
        SystemDeviceDataComponent,
        SystemDeviceDataUpdateComponent,
        SystemDeviceDataDeleteDialogComponent,
        SystemDeviceDataDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WpcemSystemDeviceDataModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
