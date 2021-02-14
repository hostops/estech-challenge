/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceDataUpdateComponent } from 'app/entities/system-device-data/system-device-data-update.component';
import { SystemDeviceDataService } from 'app/entities/system-device-data/system-device-data.service';
import { SystemDeviceData } from 'app/shared/model/system-device-data.model';

describe('Component Tests', () => {
    describe('SystemDeviceData Management Update Component', () => {
        let comp: SystemDeviceDataUpdateComponent;
        let fixture: ComponentFixture<SystemDeviceDataUpdateComponent>;
        let service: SystemDeviceDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceDataUpdateComponent]
            })
                .overrideTemplate(SystemDeviceDataUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SystemDeviceDataUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemDeviceDataService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemDeviceData(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemDeviceData = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemDeviceData();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemDeviceData = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
