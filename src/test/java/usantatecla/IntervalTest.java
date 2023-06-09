package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalTest {
  
  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }
  @Test
  public void givenIntervalContainsIntervalTest() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertTrue(interval.doLimitsOverlap(interval));
  }

  @Test
  public void givenIntervalDoesNotContainLeftIntervalTest() {
    Interval intervalA = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval intervalB = this.intervalBuilder.closed(left.getLess()).closed(left.getEquals()).build();
    assertFalse(intervalA.doLimitsOverlap(intervalB));
  }

  @Test
  public void givenIntervalContainsPartialLeftIntervalTest() {
    Interval intervalA = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval intervalB = this.intervalBuilder.closed(left.getLess()).closed(left.getGreater()).build();
    assertTrue(intervalA.doLimitsOverlap(intervalB));
  }

  @Test
  public void givenIntervalDoesNotContainLimitValueTest() {
    Interval intervalA = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval intervalB = this.intervalBuilder.open(left.getLess()).open(left.getEquals()).build();
    assertFalse(intervalA.doLimitsOverlap(intervalB));
  }

  @Test
  public void givenIntervalContainsIntervalOpenTest() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertTrue(interval.doLimitsOverlap(interval));
  }

  @Test
  public void givenIntervalIntersectWiderTest() {
    Interval intervalA = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    this.intervalBuilder = new IntervalBuilder();
    Interval intervalB = this.intervalBuilder.closed(left.getLess()).closed(right.getGreater()).build();
    assertTrue(intervalA.doLimitsOverlap(intervalB));
  }

}