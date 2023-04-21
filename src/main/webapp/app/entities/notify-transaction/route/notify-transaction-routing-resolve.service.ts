import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INotifyTransaction } from '../notify-transaction.model';
import { NotifyTransactionService } from '../service/notify-transaction.service';

@Injectable({ providedIn: 'root' })
export class NotifyTransactionRoutingResolveService implements Resolve<INotifyTransaction | null> {
  constructor(protected service: NotifyTransactionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotifyTransaction | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((notifyTransaction: HttpResponse<INotifyTransaction>) => {
          if (notifyTransaction.body) {
            return of(notifyTransaction.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
