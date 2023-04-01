import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpperNavigationComponent } from './upper-navigation.component';

describe('UpperNavigationComponent', () => {
  let component: UpperNavigationComponent;
  let fixture: ComponentFixture<UpperNavigationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpperNavigationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpperNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
