import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'system-unit',
                loadChildren: './system-unit/system-unit.module#WpcemSystemUnitModule'
            },
            {
                path: 'system-device',
                loadChildren: './system-device/system-device.module#WpcemSystemDeviceModule'
            },
            {
                path: 'system-device-type',
                loadChildren: './system-device-type/system-device-type.module#WpcemSystemDeviceTypeModule'
            },
            {
                path: 'system-device-data',
                loadChildren: './system-device-data/system-device-data.module#WpcemSystemDeviceDataModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class WpcemEntityModule {}
