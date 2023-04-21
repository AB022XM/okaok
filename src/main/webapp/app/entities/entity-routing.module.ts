import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'notify-transaction',
        data: { pageTitle: 'collectionsAppv1App.notifyTransaction.home.title' },
        loadChildren: () => import('./notify-transaction/notify-transaction.module').then(m => m.NotifyTransactionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
