import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NotifyTransactionFormService } from './notify-transaction-form.service';
import { NotifyTransactionService } from '../service/notify-transaction.service';
import { INotifyTransaction } from '../notify-transaction.model';

import { NotifyTransactionUpdateComponent } from './notify-transaction-update.component';

describe('NotifyTransaction Management Update Component', () => {
  let comp: NotifyTransactionUpdateComponent;
  let fixture: ComponentFixture<NotifyTransactionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let notifyTransactionFormService: NotifyTransactionFormService;
  let notifyTransactionService: NotifyTransactionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [NotifyTransactionUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(NotifyTransactionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NotifyTransactionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    notifyTransactionFormService = TestBed.inject(NotifyTransactionFormService);
    notifyTransactionService = TestBed.inject(NotifyTransactionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const notifyTransaction: INotifyTransaction = { id: 456 };

      activatedRoute.data = of({ notifyTransaction });
      comp.ngOnInit();

      expect(comp.notifyTransaction).toEqual(notifyTransaction);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INotifyTransaction>>();
      const notifyTransaction = { id: 123 };
      jest.spyOn(notifyTransactionFormService, 'getNotifyTransaction').mockReturnValue(notifyTransaction);
      jest.spyOn(notifyTransactionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ notifyTransaction });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: notifyTransaction }));
      saveSubject.complete();

      // THEN
      expect(notifyTransactionFormService.getNotifyTransaction).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(notifyTransactionService.update).toHaveBeenCalledWith(expect.objectContaining(notifyTransaction));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INotifyTransaction>>();
      const notifyTransaction = { id: 123 };
      jest.spyOn(notifyTransactionFormService, 'getNotifyTransaction').mockReturnValue({ id: null });
      jest.spyOn(notifyTransactionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ notifyTransaction: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: notifyTransaction }));
      saveSubject.complete();

      // THEN
      expect(notifyTransactionFormService.getNotifyTransaction).toHaveBeenCalled();
      expect(notifyTransactionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<INotifyTransaction>>();
      const notifyTransaction = { id: 123 };
      jest.spyOn(notifyTransactionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ notifyTransaction });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(notifyTransactionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
