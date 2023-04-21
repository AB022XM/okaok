import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { INotifyTransaction } from '../notify-transaction.model';
import { NotifyTransactionService } from '../service/notify-transaction.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './notify-transaction-delete-dialog.component.html',
})
export class NotifyTransactionDeleteDialogComponent {
  notifyTransaction?: INotifyTransaction;

  constructor(protected notifyTransactionService: NotifyTransactionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notifyTransactionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
