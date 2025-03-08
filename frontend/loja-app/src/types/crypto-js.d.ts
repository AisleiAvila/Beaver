declare module 'crypto-js' {
  interface CipherParams {
    ciphertext: WordArray;
    key: WordArray;
    iv: WordArray;
    salt: WordArray;
    algorithm: unknown;
    mode: unknown;
    padding: unknown;
    blockSize: number;
    formatter: unknown;
  }

  interface WordArray {
    words: number[];
    sigBytes: number;
    toString(encoder?: unknown): string;
    concat(wordArray: WordArray): WordArray;
    clamp(): void;
    clone(): WordArray;
  }

  interface Encoder {
    stringify(wordArray: WordArray): string;
    parse(str: string): WordArray;
  }

  namespace AES {
    function encrypt(
      message: string | WordArray,
      key: string | WordArray,
      cfg?: unknown
    ): CipherParams;
    function decrypt(
      cipherParams: CipherParams | string,
      key: string | WordArray,
      cfg?: unknown
    ): WordArray;
  }

  namespace enc {
    const Hex: Encoder;
    const Latin1: Encoder;
    const Utf8: Encoder;
    const Utf16: Encoder;
    const Utf16BE: Encoder;
    const Utf16LE: Encoder;
    const Base64: Encoder;
  }
}
