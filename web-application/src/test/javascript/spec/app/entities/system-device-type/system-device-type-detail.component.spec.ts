/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceTypeDetailComponent } from 'app/entities/system-device-type/system-device-type-detail.component';
import { SystemDeviceType } from 'app/shared/model/system-device-type.model';

describe('Component Tests', () => {
    describe('SystemDeviceType Management Detail Component', () => {
        let comp: SystemDeviceTypeDetailComponent;
        let fixture: ComponentFixture<SystemDeviceTypeDetailComponent>;
        const route = ({ data: of({ systemDeviceType: new SystemDeviceType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SystemDeviceTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemDeviceTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.systemDeviceType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
