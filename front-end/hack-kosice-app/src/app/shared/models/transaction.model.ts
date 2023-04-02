export interface Transaction {
  id: number;
  personId: number;
  date: string;
  category: string;
  subcategory: string;
  description: string;
  amount: string;
  type: string;
  balanceBefore: number;
  balanceAfter: number;
  label: string;
}
