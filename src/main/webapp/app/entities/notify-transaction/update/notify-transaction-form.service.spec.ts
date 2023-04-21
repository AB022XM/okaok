import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../notify-transaction.test-samples';

import { NotifyTransactionFormService } from './notify-transaction-form.service';

describe('NotifyTransaction Form Service', () => {
  let service: NotifyTransactionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotifyTransactionFormService);
  });

  describe('Service methods', () => {
    describe('createNotifyTransactionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createNotifyTransactionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            recordUniqueIdentifier: expect.any(Object),
            paymentId: expect.any(Object),
            customerPaymentCode: expect.any(Object),
            serviceCode: expect.any(Object),
            transactionAmount: expect.any(Object),
            charges: expect.any(Object),
            partnerCode: expect.any(Object),
            timestamp: expect.any(Object),
            narration: expect.any(Object),
            currency: expect.any(Object),
            debitAccount: expect.any(Object),
            creditAccount: expect.any(Object),
            proccessingStatus: expect.any(Object),
            fcrTransactionStatus: expect.any(Object),
            fcrTransactionId: expect.any(Object),
            fcrTransactionReference: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
          })
        );
      });

      it('passing INotifyTransaction should create a new form with FormGroup', () => {
        const formGroup = service.createNotifyTransactionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            recordUniqueIdentifier: expect.any(Object),
            paymentId: expect.any(Object),
            customerPaymentCode: expect.any(Object),
            serviceCode: expect.any(Object),
            transactionAmount: expect.any(Object),
            charges: expect.any(Object),
            partnerCode: expect.any(Object),
            timestamp: expect.any(Object),
            narration: expect.any(Object),
            currency: expect.any(Object),
            debitAccount: expect.any(Object),
            creditAccount: expect.any(Object),
            proccessingStatus: expect.any(Object),
            fcrTransactionStatus: expect.any(Object),
            fcrTransactionId: expect.any(Object),
            fcrTransactionReference: expect.any(Object),
            freeField1: expect.any(Object),
            freeField2: expect.any(Object),
          })
        );
      });
    });

    describe('getNotifyTransaction', () => {
      it('should return NewNotifyTransaction for default NotifyTransaction initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createNotifyTransactionFormGroup(sampleWithNewData);

        const notifyTransaction = service.getNotifyTransaction(formGroup) as any;

        expect(notifyTransaction).toMatchObject(sampleWithNewData);
      });

      it('should return NewNotifyTransaction for empty NotifyTransaction initial value', () => {
        const formGroup = service.createNotifyTransactionFormGroup();

        const notifyTransaction = service.getNotifyTransaction(formGroup) as any;

        expect(notifyTransaction).toMatchObject({});
      });

      it('should return INotifyTransaction', () => {
        const formGroup = service.createNotifyTransactionFormGroup(sampleWithRequiredData);

        const notifyTransaction = service.getNotifyTransaction(formGroup) as any;

        expect(notifyTransaction).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing INotifyTransaction should not enable id FormControl', () => {
        const formGroup = service.createNotifyTransactionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewNotifyTransaction should disable id FormControl', () => {
        const formGroup = service.createNotifyTransactionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
