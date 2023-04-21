import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INotifyTransaction, NewNotifyTransaction } from '../notify-transaction.model';

export type PartialUpdateNotifyTransaction = Partial<INotifyTransaction> & Pick<INotifyTransaction, 'id'>;

type RestOf<T extends INotifyTransaction | NewNotifyTransaction> = Omit<T, 'timestamp'> & {
  timestamp?: string | null;
};

export type RestNotifyTransaction = RestOf<INotifyTransaction>;

export type NewRestNotifyTransaction = RestOf<NewNotifyTransaction>;

export type PartialUpdateRestNotifyTransaction = RestOf<PartialUpdateNotifyTransaction>;

export type EntityResponseType = HttpResponse<INotifyTransaction>;
export type EntityArrayResponseType = HttpResponse<INotifyTransaction[]>;

@Injectable({ providedIn: 'root' })
export class NotifyTransactionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/notify-transactions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(notifyTransaction: NewNotifyTransaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notifyTransaction);
    return this.http
      .post<RestNotifyTransaction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(notifyTransaction: INotifyTransaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notifyTransaction);
    return this.http
      .put<RestNotifyTransaction>(`${this.resourceUrl}/${this.getNotifyTransactionIdentifier(notifyTransaction)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(notifyTransaction: PartialUpdateNotifyTransaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notifyTransaction);
    return this.http
      .patch<RestNotifyTransaction>(`${this.resourceUrl}/${this.getNotifyTransactionIdentifier(notifyTransaction)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestNotifyTransaction>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestNotifyTransaction[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getNotifyTransactionIdentifier(notifyTransaction: Pick<INotifyTransaction, 'id'>): number {
    return notifyTransaction.id;
  }

  compareNotifyTransaction(o1: Pick<INotifyTransaction, 'id'> | null, o2: Pick<INotifyTransaction, 'id'> | null): boolean {
    return o1 && o2 ? this.getNotifyTransactionIdentifier(o1) === this.getNotifyTransactionIdentifier(o2) : o1 === o2;
  }

  addNotifyTransactionToCollectionIfMissing<Type extends Pick<INotifyTransaction, 'id'>>(
    notifyTransactionCollection: Type[],
    ...notifyTransactionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const notifyTransactions: Type[] = notifyTransactionsToCheck.filter(isPresent);
    if (notifyTransactions.length > 0) {
      const notifyTransactionCollectionIdentifiers = notifyTransactionCollection.map(
        notifyTransactionItem => this.getNotifyTransactionIdentifier(notifyTransactionItem)!
      );
      const notifyTransactionsToAdd = notifyTransactions.filter(notifyTransactionItem => {
        const notifyTransactionIdentifier = this.getNotifyTransactionIdentifier(notifyTransactionItem);
        if (notifyTransactionCollectionIdentifiers.includes(notifyTransactionIdentifier)) {
          return false;
        }
        notifyTransactionCollectionIdentifiers.push(notifyTransactionIdentifier);
        return true;
      });
      return [...notifyTransactionsToAdd, ...notifyTransactionCollection];
    }
    return notifyTransactionCollection;
  }

  protected convertDateFromClient<T extends INotifyTransaction | NewNotifyTransaction | PartialUpdateNotifyTransaction>(
    notifyTransaction: T
  ): RestOf<T> {
    return {
      ...notifyTransaction,
      timestamp: notifyTransaction.timestamp?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restNotifyTransaction: RestNotifyTransaction): INotifyTransaction {
    return {
      ...restNotifyTransaction,
      timestamp: restNotifyTransaction.timestamp ? dayjs(restNotifyTransaction.timestamp) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestNotifyTransaction>): HttpResponse<INotifyTransaction> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestNotifyTransaction[]>): HttpResponse<INotifyTransaction[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
