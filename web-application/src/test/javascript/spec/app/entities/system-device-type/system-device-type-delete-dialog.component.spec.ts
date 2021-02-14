/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceTypeDeleteDialogComponent } from 'app/entities/system-device-type/system-device-type-delete-dialog.component';
import { SystemDeviceTypeService } from 'app/entities/system-device-type/system-device-type.service';

describe('Component Tests', () => {
    describe('SystemDeviceType Management Delete Component', () => {
        let comp: SystemDeviceTypeDeleteDialogComponent;
        let fixture: ComponentFixture<SystemDeviceTypeDeleteDialogComponent>;
        let service: SystemDeviceTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceTypeDeleteDialogComponent]
            })
                .overrideTemplate(SystemDeviceTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemDeviceTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemDeviceTypeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
