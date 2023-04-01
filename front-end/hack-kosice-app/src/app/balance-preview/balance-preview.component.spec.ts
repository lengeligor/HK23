import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BalancePreviewComponent } from './balance-preview.component';

describe('BalancePreviewComponent', () => {
  let component: BalancePreviewComponent;
  let fixture: ComponentFixture<BalancePreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BalancePreviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BalancePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
