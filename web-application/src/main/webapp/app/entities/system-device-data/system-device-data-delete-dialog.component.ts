import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISystemDeviceData } from 'app/shared/model/system-device-data.model';
import { SystemDeviceDataService } from './system-device-data.service';

@Component({
    selector: 'jhi-system-device-data-delete-dialog',
    templateUrl: './system-device-data-delete-dialog.component.html'
})
export class SystemDeviceDataDeleteDialogComponent {
    systemDeviceData: ISystemDeviceData;

    constructor(
        protected systemDeviceDataService: SystemDeviceDataService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.systemDeviceDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'systemDeviceDataListModification',
                content: 'Deleted an systemDeviceData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-system-device-data-delete-popup',
    template: ''
})
export class SystemDeviceDataDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemDeviceData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SystemDeviceDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.systemDeviceData = systemDeviceData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/system-device-data', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/system-device-data', { outlets: { popup: null } }]);
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
