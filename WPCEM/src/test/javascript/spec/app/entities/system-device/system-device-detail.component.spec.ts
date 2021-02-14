/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceDetailComponent } from 'app/entities/system-device/system-device-detail.component';
import { SystemDevice } from 'app/shared/model/system-device.model';

describe('Component Tests', () => {
    describe('SystemDevice Management Detail Component', () => {
        let comp: SystemDeviceDetailComponent;
        let fixture: ComponentFixture<SystemDeviceDetailComponent>;
        const route = ({ data: of({ systemDevice: new SystemDevice(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SystemDeviceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemDeviceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.systemDevice).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
