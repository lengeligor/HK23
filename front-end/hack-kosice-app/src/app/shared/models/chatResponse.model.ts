export interface chatResponse {
  id: string;
  object: string;
  created: string;
  state: string;
  choices: [
    {
      index: number;
      text: string;
      finish_reason: string;
    }
  ];
}
