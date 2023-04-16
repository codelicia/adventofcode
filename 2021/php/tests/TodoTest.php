<?php

declare(strict_types=1);

namespace Codelicia\AOC2021;

use Codelicia\Aoc2021\Todo;
use Exception;
use PHPUnit\Framework\TestCase;

class TodoTest extends TestCase
{
    /**
     * @test
     * @covers Todo::class
     */
    public function itShouldBeDone(): void
    {
        $this->expectException(Exception::class);
        (new Todo())->todo();
    }
}
