import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NotifyTransactionComponent } from './list/notify-transaction.component';
import { NotifyTransactionDetailComponent } from './detail/notify-transaction-detail.component';
import { NotifyTransactionUpdateComponent } from './update/notify-transaction-update.component';
import { NotifyTransactionDeleteDialogComponent } from './delete/notify-transaction-delete-dialog.component';
import { NotifyTransactionRoutingModule } from './route/notify-transaction-routing.module';

@NgModule({
  imports: [SharedModule, NotifyTransactionRoutingModule],
  declarations: [
    NotifyTransactionComponent,
    NotifyTransactionDetailComponent,
    NotifyTransactionUpdateComponent,
    NotifyTransactionDeleteDialogComponent,
  ],
})
export class NotifyTransactionModule {}
