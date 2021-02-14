/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WpcemTestModule } from '../../../test.module';
import { SystemUnitDetailComponent } from 'app/entities/system-unit/system-unit-detail.component';
import { SystemUnit } from 'app/shared/model/system-unit.model';

describe('Component Tests', () => {
    describe('SystemUnit Management Detail Component', () => {
        let comp: SystemUnitDetailComponent;
        let fixture: ComponentFixture<SystemUnitDetailComponent>;
        const route = ({ data: of({ systemUnit: new SystemUnit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [WpcemTestModule],
                declarations: [SystemUnitDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SystemUnitDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SystemUnitDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.systemUnit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
