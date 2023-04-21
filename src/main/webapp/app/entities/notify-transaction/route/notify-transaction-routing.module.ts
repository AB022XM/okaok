import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NotifyTransactionComponent } from '../list/notify-transaction.component';
import { NotifyTransactionDetailComponent } from '../detail/notify-transaction-detail.component';
import { NotifyTransactionUpdateComponent } from '../update/notify-transaction-update.component';
import { NotifyTransactionRoutingResolveService } from './notify-transaction-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const notifyTransactionRoute: Routes = [
  {
    path: '',
    component: NotifyTransactionComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotifyTransactionDetailComponent,
    resolve: {
      notifyTransaction: NotifyTransactionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NotifyTransactionUpdateComponent,
    resolve: {
      notifyTransaction: NotifyTransactionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NotifyTransactionUpdateComponent,
    resolve: {
      notifyTransaction: NotifyTransactionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(notifyTransactionRoute)],
  exports: [RouterModule],
})
export class NotifyTransactionRoutingModule {}
