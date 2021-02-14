import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISystemDeviceType } from 'app/shared/model/system-device-type.model';
import { SystemDeviceTypeService } from './system-device-type.service';

@Component({
    selector: 'jhi-system-device-type-delete-dialog',
    templateUrl: './system-device-type-delete-dialog.component.html'
})
export class SystemDeviceTypeDeleteDialogComponent {
    systemDeviceType: ISystemDeviceType;

    constructor(
        protected systemDeviceTypeService: SystemDeviceTypeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.systemDeviceTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'systemDeviceTypeListModification',
                content: 'Deleted an systemDeviceType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-system-device-type-delete-popup',
    template: ''
})
export class SystemDeviceTypeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemDeviceType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SystemDeviceTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.systemDeviceType = systemDeviceType;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/system-device-type', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/system-device-type', { outlets: { popup: null } }]);
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
