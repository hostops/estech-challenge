/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceDataDetailComponent } from 'app/entities/system-device-data/system-device-data-detail.component';
import { SystemDeviceData } from 'app/shared/model/system-device-data.model';

describe('Component Tests', () => {
    describe('SystemDeviceData Management Detail Component', () => {
        let comp: SystemDeviceDataDetailComponent;
        let fixture: ComponentFixture<SystemDeviceDataDetailComponent>;
        const route = ({ data: of({ systemDeviceData: new SystemDeviceData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SystemDeviceDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemDeviceDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.systemDeviceData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
