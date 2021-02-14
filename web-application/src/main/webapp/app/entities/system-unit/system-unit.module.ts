import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { WpcemSharedModule } from 'app/shared';
import {
    SystemUnitComponent,
    SystemUnitDetailComponent,
    SystemUnitUpdateComponent,
    SystemUnitDeletePopupComponent,
    SystemUnitDeleteDialogComponent,
    systemUnitRoute,
    systemUnitPopupRoute
} from './';

const ENTITY_STATES = [...systemUnitRoute, ...systemUnitPopupRoute];

@NgModule({
    imports: [WpcemSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SystemUnitComponent,
        SystemUnitDetailComponent,
        SystemUnitUpdateComponent,
        SystemUnitDeleteDialogComponent,
        SystemUnitDeletePopupComponent
    ],
    entryComponents: [SystemUnitComponent, SystemUnitUpdateComponent, SystemUnitDeleteDialogComponent, SystemUnitDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WpcemSystemUnitModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
