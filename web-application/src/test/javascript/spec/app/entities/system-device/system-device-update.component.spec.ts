/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceUpdateComponent } from 'app/entities/system-device/system-device-update.component';
import { SystemDeviceService } from 'app/entities/system-device/system-device.service';
import { SystemDevice } from 'app/shared/model/system-device.model';

describe('Component Tests', () => {
    describe('SystemDevice Management Update Component', () => {
        let comp: SystemDeviceUpdateComponent;
        let fixture: ComponentFixture<SystemDeviceUpdateComponent>;
        let service: SystemDeviceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceUpdateComponent]
            })
                .overrideTemplate(SystemDeviceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SystemDeviceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemDeviceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemDevice(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemDevice = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemDevice();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemDevice = entity;
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
