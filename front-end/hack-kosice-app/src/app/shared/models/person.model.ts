import { Address } from './address.model';

export interface Person {
  id: number;
  name: string;
  lastname: string;
  email: string;
  password: string;
  type: string;
  gender: string;
  age: number;
  dateOfBirdth: string;
  balance: number;
  address: Address;
}
