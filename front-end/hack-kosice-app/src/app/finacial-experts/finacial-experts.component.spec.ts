import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinacialExpertsComponent } from './finacial-experts.component';

describe('FinacialExpertsComponent', () => {
  let component: FinacialExpertsComponent;
  let fixture: ComponentFixture<FinacialExpertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinacialExpertsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FinacialExpertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
