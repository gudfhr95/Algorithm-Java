class Solution {

  public boolean validUtf8(int[] data) {
    int index = 0;

    while (index < data.length) {
      int length = getLength(data[index]);
      System.out.println(length);

      if (length == -1) {
        return false;
      }

      if (!isValid(data, index, length)) {
        return false;
      }

      index += length;
    }

    return true;
  }

  public int getLength(int n) {
    int result = 0;

    while (n > 0) {
      if ((n & 0x80) == 0) {
        break;
      }

      result += 1;
      n <<= 1;
    }

    if (result == 1) {
      return -1;
    }

    if (result > 4) {
      return -1;
    }

    if (result == 0) {
      result += 1;
    }

    return result;
  }

  public boolean isValid(int[] data, int index, int length) {
    if (index + length > data.length) {
      return false;
    }

    for (int i = index + 1; i < index + length; i++) {
      if ((data[i] & 0x80) != 0x80) {
        return false;
      }
    }

    return true;
  }
}