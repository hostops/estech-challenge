import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISystemUnit } from 'app/shared/model/system-unit.model';
import { SystemUnitService } from './system-unit.service';

@Component({
    selector: 'jhi-system-unit-delete-dialog',
    templateUrl: './system-unit-delete-dialog.component.html'
})
export class SystemUnitDeleteDialogComponent {
    systemUnit: ISystemUnit;

    constructor(
        protected systemUnitService: SystemUnitService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.systemUnitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'systemUnitListModification',
                content: 'Deleted an systemUnit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-system-unit-delete-popup',
    template: ''
})
export class SystemUnitDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemUnit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SystemUnitDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.systemUnit = systemUnit;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/system-unit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/system-unit', { outlets: { popup: null } }]);
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
