import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { INotifyTransaction } from '../notify-transaction.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../notify-transaction.test-samples';

import { NotifyTransactionService, RestNotifyTransaction } from './notify-transaction.service';

const requireRestSample: RestNotifyTransaction = {
  ...sampleWithRequiredData,
  timestamp: sampleWithRequiredData.timestamp?.format(DATE_FORMAT),
};

describe('NotifyTransaction Service', () => {
  let service: NotifyTransactionService;
  let httpMock: HttpTestingController;
  let expectedResult: INotifyTransaction | INotifyTransaction[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NotifyTransactionService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a NotifyTransaction', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const notifyTransaction = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(notifyTransaction).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a NotifyTransaction', () => {
      const notifyTransaction = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(notifyTransaction).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a NotifyTransaction', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of NotifyTransaction', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a NotifyTransaction', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addNotifyTransactionToCollectionIfMissing', () => {
      it('should add a NotifyTransaction to an empty array', () => {
        const notifyTransaction: INotifyTransaction = sampleWithRequiredData;
        expectedResult = service.addNotifyTransactionToCollectionIfMissing([], notifyTransaction);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(notifyTransaction);
      });

      it('should not add a NotifyTransaction to an array that contains it', () => {
        const notifyTransaction: INotifyTransaction = sampleWithRequiredData;
        const notifyTransactionCollection: INotifyTransaction[] = [
          {
            ...notifyTransaction,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addNotifyTransactionToCollectionIfMissing(notifyTransactionCollection, notifyTransaction);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a NotifyTransaction to an array that doesn't contain it", () => {
        const notifyTransaction: INotifyTransaction = sampleWithRequiredData;
        const notifyTransactionCollection: INotifyTransaction[] = [sampleWithPartialData];
        expectedResult = service.addNotifyTransactionToCollectionIfMissing(notifyTransactionCollection, notifyTransaction);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(notifyTransaction);
      });

      it('should add only unique NotifyTransaction to an array', () => {
        const notifyTransactionArray: INotifyTransaction[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const notifyTransactionCollection: INotifyTransaction[] = [sampleWithRequiredData];
        expectedResult = service.addNotifyTransactionToCollectionIfMissing(notifyTransactionCollection, ...notifyTransactionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const notifyTransaction: INotifyTransaction = sampleWithRequiredData;
        const notifyTransaction2: INotifyTransaction = sampleWithPartialData;
        expectedResult = service.addNotifyTransactionToCollectionIfMissing([], notifyTransaction, notifyTransaction2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(notifyTransaction);
        expect(expectedResult).toContain(notifyTransaction2);
      });

      it('should accept null and undefined values', () => {
        const notifyTransaction: INotifyTransaction = sampleWithRequiredData;
        expectedResult = service.addNotifyTransactionToCollectionIfMissing([], null, notifyTransaction, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(notifyTransaction);
      });

      it('should return initial array if no NotifyTransaction is added', () => {
        const notifyTransactionCollection: INotifyTransaction[] = [sampleWithRequiredData];
        expectedResult = service.addNotifyTransactionToCollectionIfMissing(notifyTransactionCollection, undefined, null);
        expect(expectedResult).toEqual(notifyTransactionCollection);
      });
    });

    describe('compareNotifyTransaction', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareNotifyTransaction(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareNotifyTransaction(entity1, entity2);
        const compareResult2 = service.compareNotifyTransaction(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareNotifyTransaction(entity1, entity2);
        const compareResult2 = service.compareNotifyTransaction(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareNotifyTransaction(entity1, entity2);
        const compareResult2 = service.compareNotifyTransaction(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
