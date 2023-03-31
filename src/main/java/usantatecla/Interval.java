package usantatecla;

public class Interval {

	private Min min;
	private Max max;

	public Interval(Min min, Max max) {
		assert min.value <= max.value;
		this.min = min;
		this.max = max;
	}

	public boolean include(double value) {
			return this.min.isWithin(value) && this.max.isWithin(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval other = (Interval) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.min.toString() + ", " + max.toString();
	}

	public boolean doLimitsOverlap(Interval candidateInterval) {
		if (this.areLimitsEqual(candidateInterval)) {
			return true;
		}
		if (this.areLimitsCrossed(candidateInterval)) {
			return this.isMutualLimitContained(candidateInterval);
		}
		return (this.include(candidateInterval.min.value) || this.include(candidateInterval.max.value))
				|| (this.min.value >= candidateInterval.min.value && this.max.value <= candidateInterval.max.value);
	}

	private boolean isMutualLimitContained(Interval candidateInterval) {
		double minVal = candidateInterval.min.value;
		double maxVal = candidateInterval.max.value;
		return (this.include(minVal) && candidateInterval.include(minVal))
				|| (this.include(maxVal) && candidateInterval.include(maxVal));
	}

	private boolean areLimitsEqual(Interval candidateInterval) {
		return this.min.equals(candidateInterval.min) || this.max.equals(candidateInterval.max);
	}

	private boolean areLimitsCrossed(Interval candidateInterval) {
		return this.min.value == candidateInterval.max.value || this.max.value == candidateInterval.min.value;
	}

}