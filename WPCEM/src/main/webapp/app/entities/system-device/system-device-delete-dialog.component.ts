import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISystemDevice } from 'app/shared/model/system-device.model';
import { SystemDeviceService } from './system-device.service';

@Component({
    selector: 'jhi-system-device-delete-dialog',
    templateUrl: './system-device-delete-dialog.component.html'
})
export class SystemDeviceDeleteDialogComponent {
    systemDevice: ISystemDevice;

    constructor(
        protected systemDeviceService: SystemDeviceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.systemDeviceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'systemDeviceListModification',
                content: 'Deleted an systemDevice'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-system-device-delete-popup',
    template: ''
})
export class SystemDeviceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemDevice }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SystemDeviceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.systemDevice = systemDevice;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/system-device', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/system-device', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
