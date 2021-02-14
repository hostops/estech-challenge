/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { WpcemTestModule } from '../../../test.module';
import { SystemDeviceDeleteDialogComponent } from 'app/entities/system-device/system-device-delete-dialog.component';
import { SystemDeviceService } from 'app/entities/system-device/system-device.service';

describe('Component Tests', () => {
    describe('SystemDevice Management Delete Component', () => {
        let comp: SystemDeviceDeleteDialogComponent;
        let fixture: ComponentFixture<SystemDeviceDeleteDialogComponent>;
        let service: SystemDeviceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemDeviceDeleteDialogComponent]
            })
                .overrideTemplate(SystemDeviceDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemDeviceDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SystemDeviceService);
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
