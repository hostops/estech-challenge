/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceTypeUpdateComponent } from 'app/entities/system-device-type/system-device-type-update.component';
import { SystemDeviceTypeService } from 'app/entities/system-device-type/system-device-type.service';
import { SystemDeviceType } from 'app/shared/model/system-device-type.model';

describe('Component Tests', () => {
    describe('SystemDeviceType Management Update Component', () => {
        let comp: SystemDeviceTypeUpdateComponent;
        let fixture: ComponentFixture<SystemDeviceTypeUpdateComponent>;
        let service: SystemDeviceTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceTypeUpdateComponent]
            })
                .overrideTemplate(SystemDeviceTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SystemDeviceTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemDeviceTypeService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemDeviceType(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemDeviceType = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemDeviceType();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemDeviceType = entity;
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
