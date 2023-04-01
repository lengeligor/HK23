import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideAdvisorComponent } from './side-advisor.component';

describe('SideAdvisorComponent', () => {
  let component: SideAdvisorComponent;
  let fixture: ComponentFixture<SideAdvisorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SideAdvisorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SideAdvisorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
