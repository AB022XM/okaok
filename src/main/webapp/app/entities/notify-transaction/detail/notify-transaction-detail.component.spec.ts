import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NotifyTransactionDetailComponent } from './notify-transaction-detail.component';

describe('NotifyTransaction Management Detail Component', () => {
  let comp: NotifyTransactionDetailComponent;
  let fixture: ComponentFixture<NotifyTransactionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NotifyTransactionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ notifyTransaction: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(NotifyTransactionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NotifyTransactionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load notifyTransaction on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.notifyTransaction).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
