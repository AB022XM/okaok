import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { NotifyTransactionFormService, NotifyTransactionFormGroup } from './notify-transaction-form.service';
import { INotifyTransaction } from '../notify-transaction.model';
import { NotifyTransactionService } from '../service/notify-transaction.service';
import { ProccesingStatus } from 'app/entities/enumerations/proccesing-status.model';
import { TransactionStatus } from 'app/entities/enumerations/transaction-status.model';

@Component({
  selector: 'jhi-notify-transaction-update',
  templateUrl: './notify-transaction-update.component.html',
})
export class NotifyTransactionUpdateComponent implements OnInit {
  isSaving = false;
  notifyTransaction: INotifyTransaction | null = null;
  proccesingStatusValues = Object.keys(ProccesingStatus);
  transactionStatusValues = Object.keys(TransactionStatus);

  editForm: NotifyTransactionFormGroup = this.notifyTransactionFormService.createNotifyTransactionFormGroup();

  constructor(
    protected notifyTransactionService: NotifyTransactionService,
    protected notifyTransactionFormService: NotifyTransactionFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notifyTransaction }) => {
      this.notifyTransaction = notifyTransaction;
      if (notifyTransaction) {
        this.updateForm(notifyTransaction);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notifyTransaction = this.notifyTransactionFormService.getNotifyTransaction(this.editForm);
    if (notifyTransaction.id !== null) {
      this.subscribeToSaveResponse(this.notifyTransactionService.update(notifyTransaction));
    } else {
      this.subscribeToSaveResponse(this.notifyTransactionService.create(notifyTransaction));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotifyTransaction>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(notifyTransaction: INotifyTransaction): void {
    this.notifyTransaction = notifyTransaction;
    this.notifyTransactionFormService.resetForm(this.editForm, notifyTransaction);
  }
}
