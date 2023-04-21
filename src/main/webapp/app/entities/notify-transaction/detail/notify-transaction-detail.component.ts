import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotifyTransaction } from '../notify-transaction.model';

@Component({
  selector: 'jhi-notify-transaction-detail',
  templateUrl: './notify-transaction-detail.component.html',
})
export class NotifyTransactionDetailComponent implements OnInit {
  notifyTransaction: INotifyTransaction | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notifyTransaction }) => {
      this.notifyTransaction = notifyTransaction;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
