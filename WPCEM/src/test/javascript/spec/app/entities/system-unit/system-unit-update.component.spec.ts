/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemUnitUpdateComponent } from 'app/entities/system-unit/system-unit-update.component';
import { SystemUnitService } from 'app/entities/system-unit/system-unit.service';
import { SystemUnit } from 'app/shared/model/system-unit.model';

describe('Component Tests', () => {
    describe('SystemUnit Management Update Component', () => {
        let comp: SystemUnitUpdateComponent;
        let fixture: ComponentFixture<SystemUnitUpdateComponent>;
        let service: SystemUnitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemUnitUpdateComponent]
            })
                .overrideTemplate(SystemUnitUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SystemUnitUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemUnitService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemUnit(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemUnit = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SystemUnit();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.systemUnit = entity;
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
