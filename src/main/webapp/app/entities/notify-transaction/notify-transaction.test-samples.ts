import dayjs from 'dayjs/esm';

import { ProccesingStatus } from 'app/entities/enumerations/proccesing-status.model';
import { TransactionStatus } from 'app/entities/enumerations/transaction-status.model';

import { INotifyTransaction, NewNotifyTransaction } from './notify-transaction.model';

export const sampleWithRequiredData: INotifyTransaction = {
  id: 9127,
  recordUniqueIdentifier: '2b3efb16-c095-4f5a-aea8-96cd31bd3527',
  paymentId: 218,
  customerPaymentCode: 'Tokelau',
  serviceCode: 3672,
  transactionAmount: 1038,
  partnerCode: 'Borders',
  timestamp: dayjs('2023-04-21'),
  narration: 'parse',
  currency: 30701,
  debitAccount: 33888,
  creditAccount: 23540,
};

export const sampleWithPartialData: INotifyTransaction = {
  id: 45489,
  recordUniqueIdentifier: '3823457d-9a10-4acf-8d03-863ea9acc558',
  paymentId: 48969,
  customerPaymentCode: 'invoice',
  serviceCode: 68381,
  transactionAmount: 31763,
  charges: 40620,
  partnerCode: 'strategy Baby Small',
  timestamp: dayjs('2023-04-20'),
  narration: 'Specialist wireless',
  currency: 68809,
  debitAccount: 65822,
  creditAccount: 15964,
  fcrTransactionId: 'JSON Louisiana granular',
  freeField1: 'reciprocal Rest Bedfordshire',
};

export const sampleWithFullData: INotifyTransaction = {
  id: 7926,
  recordUniqueIdentifier: '5d65869b-e992-40a0-b5f7-70d91a20f0fc',
  paymentId: 72940,
  customerPaymentCode: 'Computer scale Down-sized',
  serviceCode: 76912,
  transactionAmount: 16588,
  charges: 55860,
  partnerCode: 'port Rapid Streamlined',
  timestamp: dayjs('2023-04-20'),
  narration: 'payment deposit synergies',
  currency: 5603,
  debitAccount: 24079,
  creditAccount: 88168,
  proccessingStatus: ProccesingStatus['LOGGED'],
  fcrTransactionStatus: TransactionStatus['FAILED'],
  fcrTransactionId: 'Loan hack',
  fcrTransactionReference: 'visualize Borders',
  freeField1: 'reciprocal structure',
  freeField2: 'Stand-alone National',
};

export const sampleWithNewData: NewNotifyTransaction = {
  recordUniqueIdentifier: 'a8b73e91-0f44-4b6e-a001-3c8fdad66968',
  paymentId: 63135,
  customerPaymentCode: 'Chips up',
  serviceCode: 87753,
  transactionAmount: 75601,
  partnerCode: 'monetize Croatia Cotton',
  timestamp: dayjs('2023-04-21'),
  narration: 'Computer',
  currency: 693,
  debitAccount: 94830,
  creditAccount: 43190,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
