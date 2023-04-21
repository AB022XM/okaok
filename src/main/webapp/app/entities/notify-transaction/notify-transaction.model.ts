import dayjs from 'dayjs/esm';
import { ProccesingStatus } from 'app/entities/enumerations/proccesing-status.model';
import { TransactionStatus } from 'app/entities/enumerations/transaction-status.model';

export interface INotifyTransaction {
  id: number;
  recordUniqueIdentifier?: string | null;
  paymentId?: number | null;
  customerPaymentCode?: string | null;
  serviceCode?: number | null;
  transactionAmount?: number | null;
  charges?: number | null;
  partnerCode?: string | null;
  timestamp?: dayjs.Dayjs | null;
  narration?: string | null;
  currency?: number | null;
  debitAccount?: number | null;
  creditAccount?: number | null;
  proccessingStatus?: ProccesingStatus | null;
  fcrTransactionStatus?: TransactionStatus | null;
  fcrTransactionId?: string | null;
  fcrTransactionReference?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
}

export type NewNotifyTransaction = Omit<INotifyTransaction, 'id'> & { id: null };
